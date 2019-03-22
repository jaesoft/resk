FROM mcr.microsoft.com/dotnet/core/aspnet:3.0-stretch-slim AS base
LABEL maintainer "Julius Krah <juliuskrah@gmail.com>"
WORKDIR /app
EXPOSE 80

FROM mcr.microsoft.com/dotnet/core/sdk:3.0-stretch AS build
WORKDIR /src
COPY ["src/ReskGateway.csproj", "ReskGateway/"]
RUN dotnet restore "ReskGateway/ReskGateway.csproj"
COPY ./src ./ReskGateway
WORKDIR /src/ReskGateway
RUN dotnet build "ReskGateway.csproj" -c Release -o /app

FROM build AS publish
RUN dotnet publish "ReskGateway.csproj" -c Release -o /app

FROM base AS final
WORKDIR /app
COPY --from=publish /app .
ENTRYPOINT ["dotnet", "ReskGateway.dll"]